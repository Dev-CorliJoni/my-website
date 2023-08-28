pipeline {
    agent any
    options {
        // Timeout counter starts AFTER agent is allocated
        timeout(time: 1, unit: 'SECONDS')
    }
    stages {
        stage('Build') {
            steps {
                npm start build
            }
        }
        stage('Build') {
            steps {
                bat("xcopy C:\\My-Source C:\\My-Destination /O /X /E /H /K")
            }
        }
    }
}