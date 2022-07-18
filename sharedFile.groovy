def call(String stageName) {
    if ("${stageName}" == 'gitClone') {
    checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Mauros75/web-applicationz']]])    
    }
    else if ("${stageName}" == 'Build') {
        sh 'mvn clean install'
    }
    else if ("${stageName}" == 'CodeQuality') {
        sh 'mvn sonar:sonar'
    }
    else if ("${stageName}" == 'BackupArtifacts') {
        sh 'mvn deploy'
        sh 'echo Running code Quality analysis'
    }
    else if ("${stageName}" == 'Authorisation') {
      timeout(time: 24, unit: 'HOURS') {
    // some block
    input message: 'Approve or decline'
}
        
    }
    else if ("${stageName}" == 'deploy') {
        sh 'echo app now ready for deployment'
        sh 'scp -i *.war ec2-user@172.10.2:/opt/tomcta9/webapps/'
    }
}
