pipeline {
    agent any
    options {
        // Timeout counter starts AFTER agent is allocated
        timeout(time: 1, unit: 'SECONDS')
    }
    stages {
        //stage('Copy Dependencies') {
            //    steps {
                    //bat("xcopy ${NAS_PHOTO_PROGRAMMING_PROJECTS}\my-website\assets ... /O /X /E /H /K")
            //    }
            //}
        stage('Build') {
            steps {
                script {
                    npm start build
                }
            }
        }
    }
}