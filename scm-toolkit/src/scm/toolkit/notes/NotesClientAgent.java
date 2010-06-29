package scm.toolkit.notes;

import java.io.File;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import scm.toolkit.Constants;
import de.bea.domingo.DDatabase;
import de.bea.domingo.DDocument;
import de.bea.domingo.DEmbeddedObject;
import de.bea.domingo.DNotesException;
import de.bea.domingo.DNotesFactory;
import de.bea.domingo.DSession;

public class NotesClientAgent {
	
	private static final Log log = LogFactory.getLog(NotesClientAgent.class);
	
	public static void run(Properties args) throws DNotesException {
		
		log.debug("ノーツクライアント環境初期化");
		
		log.debug(Constants.NOTES_SERVER_URL + ":" + args.get(Constants.NOTES_SERVER_URL));
		log.debug(Constants.NOTES_DATABASE_PATH + ":" + args.get(Constants.NOTES_DATABASE_PATH));
		log.debug(Constants.NOTES_DOCUMENT_UNID + ":" + args.get(Constants.NOTES_DOCUMENT_UNID));
		log.debug(Constants.NOTES_ATTACHEMENT_FILENAME + ":" + args.get(Constants.NOTES_ATTACHEMENT_FILENAME));
		
		DNotesFactory factory = DNotesFactory.getInstance();
		DSession session = factory.getSessionWithFullAccess(System.getProperty(Constants.NOTES_AUTH_PWD));
		DDatabase database = session.getDatabase((String)args.get(Constants.NOTES_SERVER_URL), (String)args.get(Constants.NOTES_DATABASE_PATH));
		DDocument doc =  database.getDocumentByUNID((String)args.get(Constants.NOTES_DOCUMENT_UNID));
		
		log.debug("対象データを取得中...");
		DEmbeddedObject embeddedObject = doc.getAttachment((String)args.get(Constants.NOTES_ATTACHEMENT_FILENAME));
		
		log.debug("ワーク領域へエクスポート：" + System.getProperty(Constants.LOCAL_WORK_DIR) + File.separator + args.get(Constants.NOTES_ATTACHEMENT_FILENAME));
		embeddedObject.extractFile(System.getProperty(Constants.LOCAL_WORK_DIR) + File.separator + args.get(Constants.NOTES_ATTACHEMENT_FILENAME));
		
		factory.disposeInstance();
	}
}
