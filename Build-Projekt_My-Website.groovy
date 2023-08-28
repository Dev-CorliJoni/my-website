pipeline {
    agent any
    options {
        // Timeout counter starts AFTER agent is allocated
        timeout(time: 1, unit: 'SECONDS')
    }
    stages {
        stage('Copy Dependencies') {
            steps {
                script {
                    assets_path = env.NAS_PHOTO_PROGRAMMING_PROJECTS + '\\my-website\\assets'
                    assets_dest = env.WORKSPACE + '\\src\\'
                    param = 'Copy-Item -Path “' + assets_path + '” -Destination “' + assets_dest + '” -Recurse'
                }

                echo 'Execute: powershell ' + param
                sh 'pwsh "' + param + '"'
                powershell "'" + param + "'"
                echo 'Executed: powershell ' + param

                //bat("xcopy ${env.NAS_PHOTO_PROGRAMMING_PROJECTS}\\my-website\\assets ${env.WORKSPACE} /O /X /E /H /K")

            }
        }
        stage('Build') {
            steps {
                nodejs(nodeJSInstallationName: 'NODEJS_20.5.1') {
                    sh 'npm install'
                    echo 'npm install successfully'
                    sh 'npm start build'
                    echo 'npm start build successfully'
                }
            }
        }
    }
}