pipeline {
  agent any
  parameters {
    string(name: 'REPO_URL', defaultValue: '', description: 'https://github.com/chana-narglit/jenkins.git')
    string(name: 'NAME_BRANCH', defaultValue: 'main', description: 'main')
  }
  environment {
    MASTER_BRANCH = 'main'
  }
  options {
    // לא חייב אך مفيد: timeouts גלובליים או הגבלת concurrent
    disableConcurrentBuilds()
  }
  triggers {
    cron('30 5 * * 1\n0 14 * * *')
  }
  stages {
    stage('Checkout') {
      options { timeout(time: 1, unit: 'MINUTES') }
      steps {
        echo "Starting checkout stage"
        script {
          if (params.NAME_BRANCH == env.MASTER_BRANCH) {
            checkout scm
          } else {
            git url: params.REPO_URL, branch: params.NAME_BRANCH
          }
        }
        echo "Checkout completed successfully"
      }
    }
    stage('Compile') {
      options { timeout(time: 1, unit: 'MINUTES') }
      steps {
        echo "Starting compilation stage"
        sh 'mvn compile'
        echo "Compilation stage completed successfully"
      }
    }
    stage('Test') {
      options { timeout(time: 1, unit: 'MINUTES') }
      steps {
        echo "Starting test stage"
        sh 'mvn test'
        echo "Test stage completed successfully"
      }
    }
  }
  post {
    success {
      echo "Pipeline finished SUCCESSFULLY"
    }
    failure {
      echo "Pipeline FAILED"
    }
  }
}
