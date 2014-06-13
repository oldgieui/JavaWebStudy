
from fabric.api import env, execute
from fabric.operations import local
 
env.catalina_home = "/Applications/apache-tomcat-7.0.52"
 
def hostname():
    local('uname -a')
 
def build():
    local('mvn clean package')
 
def start():
    local('%s/bin/startup.sh' % env.catalina_home)  # tomcat instance start
 
def stop():
    local('%s/bin/shutdown.sh' % env.catalina_home)  # tomcat instance stop
 
def copy():
    local('cp ./target/JavaWebStudy.war %s/webapps/JavaWebStudy.war' % env.catalina_home)  # file copy
 
def deploy(): 
    execute(build)
    execute(stop)
    execute(copy)
    execute(start)
