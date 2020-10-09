pipelineJob('jenkins_master_build') {

    properties {
        githubProjectUrl('https://github.com/TramboCloud/jenkins-workshop.git')
    }

  logRotator {
      numToKeep(5)
  }
  environmentVariables{ 
      env('ScriptPath' , 'Jenkinsfile')
  }
  parameters {
      stringParam('IMAGE_TAG', '', 'Tag used to push image to registry, if empty commit ID is used')
      stringParam('BRANCH_NAME', '', 'Used for manual deployments')
      booleanParam('DEPLOY', false)
  }
  definition {
    cpsScm {
      scm {
        git {
          remote {
                url('https://github.com/TramboCloud/jenkins-workshop.git')
            }
            branches('*/master')
        }
      }
      scriptPath('${ScriptPath}')
      lightweight(false)
    }
  }
}
