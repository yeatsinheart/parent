mvn versions:use-latest-releases -DprocessAllModules=true




cd component
cd mysql
mvn versions:use-latest-releases

cd ..
cd redis
mvn versions:use-latest-releases

cd ..
cd webflux
mvn versions:use-latest-releases

cd ..
cd dubbo
mvn versions:use-latest-releases

cd ..
cd common
mvn versions:use-latest-releases
