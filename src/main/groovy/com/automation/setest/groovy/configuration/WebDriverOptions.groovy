/*
 This file is part of the Seletest by Papadakis Giannis <gpapadakis84@gmail.com>.

 Copyright (c) 2014, Papadakis Giannis <gpapadakis84@gmail.com>
 All rights reserved.

 Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:

     * Redistributions of source code must retain the above copyright notice,
       this list of conditions and the following disclaimer.
     * Redistributions in binary form must reproduce the above copyright notice,
       this list of conditions and the following disclaimer in the documentation
       and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.automation.setest.groovy.configuration

import groovy.util.logging.*
/**
 * WebDriver various groovy functions
 * @author Giannis Papadakis (mailTo:gpapadakis84@gmail.com)
 *
 */
@Slf4j
class WebDriverOptions {

    /**
     * Download ChromeDriver or IEDriver or PhantomJS  executables in local environment if not exists using AntBuilder
     * @param file
     * @param path
     */
    public static synchronized void downloadDriver(File file, String path, String proxyHost, String proxyPort) {
        if (!file.exists()) {
            if(proxyHost != null && proxyPort != null) {
                System.properties << [ 'http.proxyHost':proxyHost, 'http.proxyPort':proxyPort ]
            }
            log.info('Download {} from Central Repo', file)
            def ant = new AntBuilder()
            ant.get(src: path, dest: 'driver.zip')
            ant.unzip(src: 'driver.zip', dest: file.parent)
            ant.delete(file: 'driver.zip')
            ant.chmod(file: file, perm: '700')
        }
        else {
            log.info('{} already exists', file)
        }
    }
}
