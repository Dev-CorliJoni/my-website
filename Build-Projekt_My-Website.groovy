pipeline {
    agent any
    options {
        // Timeout counter starts AFTER agent is allocated
        timeout(time: 1, unit: 'SECONDS')
    }
    stages {
        stage('Build') {
            stage('Copy Dependencies') {
                steps {
                    bat("xcopy ${env.NAS_PHOTO_PROGRAMMING_PROJECTS}\my-website\assets ${env.WORKSPACE} /O /X /E /H /K")
                }
            }
            steps {
                nodejs(nodeJSInstallationName: 'NODEJS_20.5.1') {
                    sh 'npm install'
                    sh 'npm start build'
                }
            }
        }
    }
}