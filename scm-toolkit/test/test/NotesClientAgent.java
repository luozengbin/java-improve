package test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.bea.domingo.DDatabase;
import de.bea.domingo.DDocument;
import de.bea.domingo.DEmbeddedObject;
import de.bea.domingo.DNotesException;
import de.bea.domingo.DNotesFactory;
import de.bea.domingo.DSession;

public class NotesClientAgent {
	
	private static final Log log = LogFactory.getLog(NotesClientAgent.class);
	
	public static void main(String[] args) throws DNotesException {
		
		log.debug("ノーツクライアント環境初期化");
		
		DNotesFactory factory = DNotesFactory.getInstance();
		DSession session = factory.getSessionWithFullAccess("freeadmin");
		DDatabase database = session.getDatabase("notes11/srv/olympus", "A31\\monopj1.nsf");
		DDocument doc =  database.getDocumentByUNID("7E78DC5FEC820EC24925764F001A879E");
		
		log.debug("対象データを取得中...");
		
		//List attList = doc.getAttachments();

		
		DEmbeddedObject embeddedObject = doc.getAttachment("テスト管理票（結合テストフェーズ）.xls");
		
		System.out.println(embeddedObject);
		
		//log.debug("ワーク領域へエクスポート：" + System.getProperty(Constants.LOCAL_WORK_DIR) + File.separator + args.get(Constants.NOTES_ATTACHEMENT_FILENAME));
		embeddedObject.extractFile("c:/aaa.xsl");
		
		factory.disposeInstance();
	}
}
