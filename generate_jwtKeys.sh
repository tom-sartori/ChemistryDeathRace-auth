folder="src/main/resources/jwt"
mkdir $folder
openssl genrsa -out $folder/rsaPrivateKey.pem 2048
openssl rsa -pubout -in $folder/rsaPrivateKey.pem -out $folder/publicKey.pem
openssl pkcs8 -topk8 -nocrypt -inform pem -in $folder/rsaPrivateKey.pem -outform pem -out $folder/privateKey.pem
chmod 600 $folder/rsaPrivateKey.pem
chmod 600 $folder/privateKey.pem