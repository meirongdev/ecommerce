#!/bin/bash
# create key pair
openssl genrsa -out keypair.pem 2048

# extract public key
openssl rsa -in keypair.pem -pubout -out public.pem

# extract private key
openssl pkcs8 -in keypair.pem -topk8 -nocrypt -inform PEM -outform PEM -out private.pem
mv private.pem src/main/resources/certs/private.pem
mv public.pem src/main/resources/certs/public.pem
mv keypair.pem src/main/resources/certs/keypair.pem