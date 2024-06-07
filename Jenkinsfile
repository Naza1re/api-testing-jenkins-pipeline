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
