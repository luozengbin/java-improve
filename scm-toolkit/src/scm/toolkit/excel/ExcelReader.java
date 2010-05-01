package scm.toolkit.excel;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

import scm.toolkit.Constants;
import scm.toolkit.util.CommonUtils;

public class ExcelReader {

	private static final Log log = LogFactory.getLog(ExcelReader.class);
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN);
	
	
	public static Map<String, Map<String, String>> extractData(Properties prop) throws Exception {
		
		FileInputStream in = null;
		
		Map<String, Map<String, String>> result = new HashMap<String, Map<String,String>>();
		
		try {
			Workbook wb = null;
			
			in = new FileInputStream(System.getProperty(Constants.LOCAL_WORK_DIR) + File.separator + prop.get(Constants.NOTES_ATTACHEMENT_FILENAME));
			
			wb = WorkbookFactory.create(in);
			
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			
			Sheet sheet = wb.getSheet(prop.getProperty(Constants.SPREADSHEET_WORKBOOK));
			
			int from = Integer.valueOf(prop.getProperty(Constants.SPREADSHEET_ROW_FROM));
			
			int to = sheet.getLastRowNum();
			
			Map<String, String> issueMapping = CommonUtils.splitAsMap(prop.getProperty(Constants.REDMINE_SPREADSHEET_ISSUE_MAPPING), ",", Constants.KEY_VALUE_PIPE_MARK);
			
			Map<String, String> valueMap = null;
			
			String cellValue = null;
			
			Row row = null;
			
			Cell cell = null;
			Cell manageNoCell = null;
			
			String manageNo = null;
			
			for (int i = from; i < to; i++) {
				
				valueMap = new HashMap<String, String>();
				
				row = sheet.getRow(i);
				
				if(row != null){
					
					manageNoCell = row.getCell(CellReference.convertColStringToIndex(issueMapping.get(prop.getProperty(Constants.REDMINE_SPREADSHEET_ISSUE_KEY))));
					
					if(manageNoCell != null){
						manageNo = getCellContent(manageNoCell, evaluator);
					}
					
					if(StringUtils.isNotEmpty(manageNo)){
						for (Entry<String, String> entry : issueMapping.entrySet()) {
							cell = row.getCell(CellReference.convertColStringToIndex(entry.getValue()));
							if(cell != null){
								cellValue = getCellContent(cell, evaluator);
								
								if(StringUtils.isNotEmpty(cellValue)){
									valueMap.put(entry.getKey(), cellValue);
								}
							}
						}
					}
				} else {
					
					break;
					
				}
				
				if(valueMap.size() > 1){
					
					log.debug(i + "行目を読み込んでいます。障害番号:#" + manageNo);
					
					result.put(manageNo, valueMap);
					
				}else{
					
					break;
					
				}
			}
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
			throw e;
			
		} finally {
			in.close();
			in = null;
		}
	}

	private static String getCellContent(Cell cell, FormulaEvaluator evaluator) {

		String result = null;

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			result = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				result = sdf.format(cell.getDateCellValue());
			} else {
				result = String.valueOf(Double.valueOf(
						cell.getNumericCellValue()).intValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			switch (evaluator.evaluateFormulaCell(cell)) {
			case Cell.CELL_TYPE_BOOLEAN:
				result = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					result = sdf.format(cell.getDateCellValue());
				} else {
					result = String.valueOf(Double.valueOf(
							cell.getNumericCellValue()).intValue());
				}
				break;
			case Cell.CELL_TYPE_STRING:
				result = String.valueOf(cell.getStringCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_ERROR:
				result = String.valueOf(cell.getErrorCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				break;
			}
			break;
		default:
			break;
		}
		return result;
	}

}
