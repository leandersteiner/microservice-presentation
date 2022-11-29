```
docker image build -t leandersteiner/msc-producer-test:latest .
docker image push leandersteiner/msc-producer-test:latest
kubectl create -f deployment.yaml
```
