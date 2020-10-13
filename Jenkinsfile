
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
              dir('jenkins'){
                if(!IMAGE_TAG.equals("")){
                  sh "docker build -f images/master/Dockerfile -t jenkins:${IMAGE_TAG} ."
                }
                sh "docker build -f images/master/Dockerfile -t jenkins:${commitId} ."
              }
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
        