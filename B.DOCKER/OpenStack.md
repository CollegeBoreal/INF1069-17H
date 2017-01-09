# OpenStack


## Set your environment Variables

```
$ source ~/Developer/canarie.ca/collège-boréal-openrc-quebec.sh
```

http://docs.openstack.org/user-guide/common/cli_set_environment_variables_using_openstack_rc.html

## Create the Docker Machine on OpenStack

```
$ docker-machine --debug \
   create --driver openstack \
          --openstack-flavor-name m1.tiny \
          --openstack-image-name "Ubuntu 14.04" \
          --openstack-ssh-user ubuntu \
          --openstack-sec-groups default  \
          --openstack-floatingip-pool nova \
          --openstack-nova-network \
          --openstack-net-name nova \
     QC-STEVE
```

## SSH into the host

```
$ ssh -o BatchMode=yes \
      -o PasswordAuthentication=no \
      -o StrictHostKeyChecking=no \
      -o UserKnownHostsFile=/dev/null \
      -o IdentitiesOnly=yes \
      -o LogLevel=quiet \
      -o ConnectionAttempts=3 -o ConnectTimeout=10 \
      -o ControlMaster=no -o ControlPath=none \
      -p 22 \
      -i ~/.docker/machine/machines/INF1069/id_rsa \
    ubuntu@<external IP address>
```
