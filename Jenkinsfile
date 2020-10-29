
// Pipeline
pipeline {
  agent any
  stages {

    stage('Setup') {
      steps {
        script {
          commitId     = sh returnStdout: true, script: 'git rev-parse HEAD'
          commitId     = commitId.trim()
        }
      }
    }
  
    stage('Docker') {
      stages {
        stage('Build') {
          steps {
            script{ 
              dir('jenkins') {
                git 'https://github.com/pnadalini/jenkins-workshop.git'
                if(!IMAGE_TAG.equals("")){
                  sh "docker build -f images/master/Dockerfile -t jenkins:${IMAGE_TAG} ."
                }
                sh "docker build -f images/master/Dockerfile -t jenkins:${commitId} ."
              }
            }
          }
        }
        stage('Deploy') {
          steps {
            script{
              if(!IMAGE_TAG.equals("")){
                sh """
                  aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 270996056496.dkr.ecr.us-east-1.amazonaws.com/jenkins
                  docker push jenkins:${IMAGE_TAG}
                """
              }
              sh """
                aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 270996056496.dkr.ecr.us-east-1.amazonaws.com/jenkins
                docker push jenkins:${commitId}
              """
            }
          }
        }
        stage ('Clean') {
          steps {
            script{
              if(!IMAGE_TAG.equals("")){
                sh "docker rmi -f jenkins:${IMAGE_TAG}"
              }
               sh "docker rmi -f jenkins:${commitId}"
            }
          }
        }
      }
    }
  }
}
        