kill -9 `ps -ef |grep java| grep stfc_api.jar | grep -v grep | awk '{print $2}'`
cd /var/app/QLDT/api
nohup /usr/bin/java -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true  -Duser.timezone=GMT+7  -Xms256m -Xmx1024m -XX:+UseParallelGC -XX:+AggressiveOpts -XX:+UseFastAccessorMethods -Dsun.net.client.defaultConnectTimeout=120000 -Dsun.net.client.defaultReadTimeout=120000  -jar stfc_api.jar > /dev/null 2>&1&
