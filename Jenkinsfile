pipeline {
    agent any

   tools {
        maven "Maven"
        jdk "JDK 17"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Naza1re/api-testing-jenkins-pipeline'
            }
        }

        stage('Build') {
            steps {
                script {

                    bat 'echo Running on %COMPUTERNAME%'
                    bat 'echo Maven version:'
                    bat 'mvn -v'
                    bat 'echo Java version:'
                    bat 'java -version'
                }
                bat 'mvn validate'
            }
        }


        stage('Test') {
            steps {

                bat 'mvn clean test -Dgroups=ok200'
                bat 'mvn clean test -Dgroups=bad400'
            }

            post {
                always {

                    archiveArtifacts artifacts: '**/target/allure-results/*', allowEmptyArchive: true
                }
                success {

                    allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
                }
            }
        }
    }
}
