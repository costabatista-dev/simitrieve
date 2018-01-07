package ml.paulobatista.simitrieve;

import java.util.List;

import ml.paulobatista.simitrieve.cmd.CMDManager;
import ml.paulobatista.simitrieve.csv.CSVManager;
import ml.paulobatista.simitrieve.entity.CosineSimilarity;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.entity.TokenList;
import ml.paulobatista.simitrieve.entity.factory.ProjectFactory;
import ml.paulobatista.simitrieve.process.Process;
import ml.paulobatista.simitrieve.similarity.SimilarityRetriever;
import ml.paulobatista.simitrieve.tokenizer.TokenManager;

/**
 * Hello world!
 *
 */

public class App {
	public static void main(String[] args) {
		CMDManager cmdManager = new CMDManager(args);
		Process process = cmdManager.getProcess();
		Project project = new ProjectFactory().getProject(cmdManager.getProjectPath(), cmdManager.getProjectName(),
				process.getProgrammingLanguage());
		project.setVersion(cmdManager.getProjectVersion());

		CSVManager csvManager = new CSVManager();

		if (args[0].equals("feature")) {
			csvManager.writeProjectFeaturesCSV(project);
		}

		else if (args[0].equals("similarity")) {
			TokenManager tokenManager = new TokenManager();

			List<TokenList> allTokenLists = tokenManager.getAllTokenList(project, process);

			List<CosineSimilarity> similarities = new SimilarityRetriever().getCosineSimilarities(allTokenLists,
					process);
			csvManager.writeProjectSimilarityCSV(similarities, project, process);
		}



	}
}
