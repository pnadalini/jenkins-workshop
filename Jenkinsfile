def dockerTag
def awsRegion     = "us-east-1"
def ecrName       = "jenkins"

// Pipeline
pipeline {
  agent { any}
  stages {

    stage('Setup') {
      steps {
        script {
          commitId     = sh returnStdout: true, script: 'git rev-parse HEAD'
          commitId     = commitId.trim()
          awsAccountId = sh (script: 'aws sts  get-caller-identity --query Account --output text ', returnStdout: true).trim()
          ecrUrl       = awsAccountId + ".dkr.ecr." + awsRegion + ".amazonaws.com/" + ecrName
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
                  sh "docker build -f images/master/Dockerfile -t ${ecrUrl}:${IMAGE_TAG} ."
                }
                sh "docker build -f images/master/Dockerfile -t ${ecrUrl}:${commitId} ."
              }
            }
          }
        }
        stage('Push') {
          steps {
            script{
              if(!IMAGE_TAG.equals("")){
                sh """
                  aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 270996056496.dkr.ecr.us-east-1.amazonaws.com/jenkins
                  docker push ${ecrUrl}:${IMAGE_TAG}
                """
              }
              sh """
                aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 270996056496.dkr.ecr.us-east-1.amazonaws.com/jenkins
                docker push ${ecrUrl}:${commitId}
              """
            }
          }
        }
        stage ('Clean') {
          steps {
            script{
              if(!IMAGE_TAG.equals("")){
                sh "docker rmi -f ${ecrUrl}:${IMAGE_TAG}"
              }
               sh "docker rmi -f ${ecrUrl}:${commitId}"
            }
          }
        }
      }
    }
  }
}
        