package utilities;

public class ExecutionEnvironment {

    public static boolean isRunningOnJenkins() {
        return System.getenv("JENKINS_HOME") != null
                || System.getenv("BUILD_NUMBER") != null
                || System.getenv("JOB_NAME") != null;
    }
}
