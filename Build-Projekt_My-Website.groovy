pipeline {
    agent any
    options {
        // Timeout counter starts AFTER agent is allocated
        timeout(time: 1, unit: 'SECONDS')
    }
    stages {
        stage('Copy Dependencies') {
            environment {
                assets_path = "${env.NAS_SHARE_PROJECTS}" + '\\my-website\\assets'
                assets_dest = "${env.WORKSPACE}" + '/src/'
            }
            steps {
                script {
                    //param = 'Copy-Item -Path “' + assets_path + '” -Destination “' + assets_dest + '” -Recurse'
                    echo "assets_path: " + assets_path
                    echo "assets_dest: " + assets_dest
                    //echo "assets_url: " + assets_url
                    
                    //def response = httpRequest url: assets_url, consoleLogResponseBody: true, httpMode: GET, timeout: 10
                    //outputFile: ...
                    //echo "Request successfully"
                    
                    node() {
                        bat("xcopy " + assets_path + " " + assets_dest + " /O /X /E /H /K")
                    //    writeFile file: (assets_dest + '/response.zip'), text: response.content
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
