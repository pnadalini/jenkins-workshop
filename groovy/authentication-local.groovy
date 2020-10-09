import jenkins.model.Jenkins
import hudson.security.*
import hudson.plugins.active_directory.*

def jenkins = Jenkins.instance

println "Jenkins database auth"
def jenkins_username = "admin"
def jenkins_password = "admin"
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount(jenkins_username,jenkins_password)
jenkins.instance.setSecurityRealm(hudsonRealm)
jenkins.save()

