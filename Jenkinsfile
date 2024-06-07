pipeline {
    agent any

    environment {
        MAVEN_OPTS = '-Dmaven.test.failure.ignore=true'
        JAVA_HOME = tool name: 'JDK 17', type: 'JDK'  // Убедитесь, что JDK 17 настроен в Jenkins
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
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
                    // Отладочные шаги
                    bat 'echo Running on %COMPUTERNAME%'
                    bat 'echo Maven version:'
                    bat 'mvn -v'
                    bat 'echo Java version:'
                    bat 'java -version'
                }
                // Сборка проекта с использованием Maven
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Запуск тестов с использованием Maven
                bat 'mvn test'
            }
            post {
                always {
                    // Архивация Allure результатов
                    archiveArtifacts artifacts: '**/target/allure-results/*', allowEmptyArchive: true
                }
                success {
                    // Публикация Allure отчета
                    allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
                }
            }
        }
    }
}
