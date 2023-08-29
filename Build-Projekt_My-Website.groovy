pipeline {
    agent any
    options {
        // Timeout counter starts AFTER agent is allocated
        timeout(time: 1, unit: 'SECONDS')
    }
    stages {
        stage('Copy Dependencies') {
            environment {
                assets_path = "${env.NAS_PHOTO_PROGRAMMING_PROJECTS}" + '\\my-website\\assets'
                assets_dest = "${env.WORKSPACE}" + '/src/'
                
                assets_url = "https://nas.home:5001/fsdownload/webapi/file_download.cgi/assets.zip"
            }
            steps {
                options {
                    timeout(time: 1, unit: 'HOURS')   // timeout on this stage
                }
                script {
                    //param = 'Copy-Item -Path “' + assets_path + '” -Destination “' + assets_dest + '” -Recurse'
                    
                    def response = httpRequest assets_url

                    echo "Requested: " + assets_url

                    node() {
                        writeFile file: (assets_dest + '/response.zip'), text: response.content
                    }
                }




                //echo "xcopy " + assets_path + " " + assets_dest + " /O /X /E /H /K"
                //bat "xcopy " + assets_path + " " + assets_dest + " /O /X /E /H /K"
                //bat "powershell.exe -NonInteractive -ExecutionPolicy Bypass -Command \"" + param + "\""
                //powershell "'" + param + "'"
                //echo 'Executed: powershell ' + param

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
