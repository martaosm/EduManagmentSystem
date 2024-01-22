### deploument na ec2
```
minikube service <nazwa serwisu> --url -n database


sudo iptables -t nat -A PREROUTING -p tcp --dport 30036 -j DNAT --to-destination 192.168.49.2:30036
sudo iptables -t nat -A POSTROUTING -j MASQUERADE

find . -name '*.yaml' -print0 | xargs -0 -L1 kubectl apply -f

```