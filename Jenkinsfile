pipeline {
    agent any

    environment {
        MAVEN_OPTS = '-Dmaven.test.failure.ignore=true'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Naza1re/api-testing-jenkins-pipeline'
            }
        }

        stage('Build') {
            steps {
                // Сборка проекта с использованием Maven
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Запуск тестов с использованием Maven
                sh 'mvn test'
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
