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
                timeout(time: 5, unit: 'MINUTES') {                    
                    script {    
                        echo 'Path: ' + assets_path
                        echo 'Dest: ' + assets_dest

                        
                        //sh('apt-get install cifs-utils')
                        //sh('mount -t cifs //nas.home/share/Projects/my-website/assets /media/nas_shared')

                        def command = 'Copy-Item -Path "//nas.home/share/Projects/my-website/assets" -Destination "' + assets_dest + '" -Verbose'
                        
                        def msg = powershell(returnStdout: true, script: command)
                        println msg
                        
                        sh('scp nas.home:/share/Projects/my-website/assets ' + assets_dest)
                        //sh('cp /media/nas_shared ' + assets_dest)
                        //sh('cp -R //nas.home/share/Projects/my-website/assets ' + assets_dest)
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
        //stage('Build') {
        //    steps {
        //       nodejs(nodeJSInstallationName: 'NODEJS_20.5.1') {
        //            sh 'npm install'
        //            echo 'npm install successfully'
        //            sh 'npm start build'
        //            echo 'npm start build successfully'
        //        }
        //    }
        //}
    }
}
