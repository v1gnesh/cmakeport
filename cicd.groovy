node('linux') 
{
        stage ('Poll') {
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        userRemoteConfigs: [[url: 'https://github.com/zopencommunity/cmakeport.git']]])
        }

        stage('Build') {
                build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/cmakeport.git'), string(name: 'PORT_DESCRIPTION', value: 'CMake is used to control the software compilation process using simple platform and compiler independent configuration files, and generate native makefiles.' )]
        }
}
