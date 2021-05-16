#mvn versions:use-latest-releases -DprocessAllModules=true
mvn versions:use-latest-releases
cd gen
mvn versions:use-latest-releases

cd ../maven
mvn versions:use-latest-releases

cd ../business/router
mvn versions:use-latest-releases
cd ../gate
mvn versions:use-latest-releases

cd ../../component/base
mvn versions:use-latest-releases

cd ../mysql
mvn versions:use-latest-releases

cd ../redis
mvn versions:use-latest-releases

cd ../webflux
mvn versions:use-latest-releases

cd ../dubbo
mvn versions:use-latest-releases

cd ../nacos
mvn versions:use-latest-releases
