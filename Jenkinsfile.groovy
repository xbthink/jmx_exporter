node {
    def mvnHome
    stage('Preparation') {
        git branch: 'master', credentialsId: 'git_account', url: 'http://xiebin@git.fancydsp.com/scm/data/jmx_exporter.git'
        mvnHome = tool 'M3'
    }
    stage('Build') {
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean install deploy -DskipTests"
        } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore -DskipTests clean package deploy /)
        }
    }
}