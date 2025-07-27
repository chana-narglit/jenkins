pipeline {
    agent any

    parameters {
        string(name: 'REPO_URL', defaultValue: 'https://github.com/chana-narglit/jenkins.git', description: 'GitHub repository')
        string(name: 'NAME_BRANCH', defaultValue: 'main', description: 'Branch to build')
    }

    environment {
        MAIN_BRANCH = 'main'
    }

    stages {
        stage('Clone repository') {
            steps {
                echo "Cloning ${params.NAME_BRANCH} from ${params.REPO_URL}"
                git branch: "${params.NAME_BRANCH}", url: "${params.REPO_URL}"
            }
        }

        stage('Compile') {
            steps {
                echo 'Starting compilation'
                sh 'mvn compile'
                echo 'Compilation completed'
            }
        }

        stage('Test') {
            steps {
                echo 'Starting tests'
                sh 'mvn test'
                echo 'Tests completed'
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded'
        }
        failure {
            echo 'Pipeline failed'
        }
    }
}
