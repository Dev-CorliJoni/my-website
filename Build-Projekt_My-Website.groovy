pipeline {
    agent any
    options {
        // Timeout counter starts AFTER agent is allocated
        timeout(time: 1, unit: 'SECONDS')
    }
    stages {
        stage('Build') {
            steps {
                withNPM() {
                    echo "Performing npm build..."
                    sh 'npm install'
                    sh 'npm start build'
                }
                script {
                    bat("npm start build")
                }
            }
        }
        //stage('Copy Dependencies') {
        //    steps {
                //bat("xcopy ${env.NAS_PHOTO_PROGRAMMING_PROJECTS}\my-website\assets ... /O /X /E /H /K")
        //    }
        //}
    }
}