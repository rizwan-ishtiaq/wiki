# Last updated on 2018-01-21


## IAM
1. IAM consists of users, roles, groups and policy documents
1. Groups is a way to group other users and apply policies to them collectively
1. Policy documents recommends Universal and they can apply on users, groups and roles
1. Policy document is consist of JSON and it is essentially a key value pair
1. IAM is universal and it does not apply to regions at this time
1. The root account is simply the account created when first setup your AWS account and it has complete admin access by default
1. When you create new users by default it have no access at all
1. New users are assigned access key ID and secret access key when first created. These are not the same as password and you cannot use the access key ID and secret access key to login into the AWS web console. You can use this to access AWS via the API and command line however
1. You only get to view credentials only once if you lose them you have to regenerate them, so save them in secure location
1. Always set up multi Factor authentication on your root account
1. You can create and customize your own password rotation policy

### Security Groups
1. All Inbound traffic is blocked by default new you create new security group
1. All outbound traffic is allowed by default for new created group
1. Changes to security group take effect immediately
1. You can have multiple security groups attached to EC2 instances, because you don’t have any deny rules, you only have allow rules so there will be no conflict.
1. Security groups are STATEFUL means if you create an inbound rule allowing traffic in, that traffic is automatically allowed back out again and vice versa.
1. You can’t block specific IP addresses using security groups, instead use network ACL

### Roles
1. Roles are much easier to manage with respect to security
1. Roles are more secure than storing your access key and secret access key on individual EC2 instances
1. Roles can be assigned to an EC2 instance after it has been provisioned using both the command line and the AWS web console
1. You can also update the policies associated with the roles at any given time and that update will be propagate at same time
1. Roles are universal you can use them in any region

## S3 - Simple Storage Service
1. Remember that S3 is object based storage. You are allowed to update files / objects. You are not allowed to install operating systems or database on S3
1. Individual Amazon S3 objects can range in size from a minimum of 0 bytes to a maximum of 5 terabytes. The largest object that can be uploaded in a single PUT is 5 gigabytes. For objects larger than 100 megabytes, customers should consider using the Multi-part Upload capability. You can load files to S3 much faster by enabling multipart upload
1. There is unlimited storage and files are stored in bucket
1. S3 is a Universal name-space that is, names must be unique globally
1. S3 URL start from S3-region-name Amazon aws.com / bucket name
1. Read after right consistency for puts of new objects
1. You will have eventual consistency for overwrite puts and deletes. Means it can take some time to propagate
1. S3 is durable, immediately available and for frequently access data
1. S3-IA durable immediately available and for infrequently access data
1. S3-reduced redundancy storage is for that data which is easily reproducible such as thumbnails etc
1. Glacier is for archive data where you can wait 3 to 5 hours before accessing it
1. S3 object made up of key, value, version ID, meta-data and Sub-resources (ACL and Torrent)
1. Key is the name of object and value is the data which you are storing on S3
1. Name of objects are lexicographical means they are store alphabetically across all the facilities available for S3
1. When you successfully uploads a object it will generate HTTP 200 status code
1. For S3 encryption we have two options server side encryption and client side encryption
1. For Service encryption, we have three subcategories server side encryption with Amazon S3 manager keys (SSE-S3). Server side encryption with KMS (SSE-KMS). Server side encryption with customer provided keys (SSE-C)
1. We can control access to bucket, using either a bucket ACL or using bucket policies
1. By default buckets are private and all objects stored inside them are private
1. Versioning stores all versions of an object including all writes and even if you delete an object
1. Versioning is a great backup tool
1. Once enabled you cannot disable versioning you can just only suspend the versioning
1. Versioning can also integrates with life cycle rules
1. Versioning’s MFA delete capability, which uses multi factor authentication, can be used to provide an additional layer of security
1. To use cross region replication versioning must be enabled on both the source and destination buckets
1. To use cross region replication you must have unique regions means you cannot copy from one bucket to another in same region using CRR, of course you can use copy command for this purpose
1. Files in an existing bucket are not replicated automatically all subsequent updated files will be replicated automatically
1. You cannot replicate to multiple buckets or use Daisy chaining
1. Delete markers are replicated. It means that if you delete from your source target and you go and look in your destination bucket. Your item will be deleted from destination and bucket as well
1. Deleting individual versions and delete markers will not be replicated in destination bucket
1. S3 life-cycle management can be used in conjunction with versioning so it means that you can turn on or turn off versioning. Life-cycle management is not depending on versioning
1. If versioning is enabled, life-cycle management can be applied to current versions as well as previous versions
1. From standard S3 you can transition to standard infrequent access storage class, but object size must be equal or greater than 128 KB and 30 days after its creation date
1. From infrequent access, you can move your object to Glacier class but it will be after 30 days the date when you moved your object to in frequent access class
1. If you are not using infrequent access storage (life-cycle management) then you can move your object to Glacier after 1 day of creation date of object
1. You can also delete your object permanently from S3 or from Glacier using life-cycle management policies
1. You can speed up transfer to S3 using S3 transfer acceleration this cost extra and has the greatest impact on the people who are in far away locations
1. You can use S3 to host static websites. These are server-less websites, very cheap, scales automatically. You cannot host dynamic websites on S3

## Cloudfront
1. Edge location is separate from AWS regions and availability zone this is the location where content will be Cached
1. Origin is the location of all the files that the CDN will distribute. This can be either an S3 bucket or EC2 instance or an elastic Load Balancer or route53. Origin can be your own server which can be in your own premises
1. Distribution consists of collection of edge locations. This is the name given to CDN
1. There are two type of distributions one is web distribution (for websites) and and other is RTMP (used for media streaming) distributions
1. Edge locations are not just read only, you can write to them as well. you can put objects on them. These objects will put on, will be backed up on origin server
1. All objects on cloudfront cached for the life of TTL (time to live)
1. You can clear the cached objects from distributions which have TTL. It will cost you

## Storage Gateway
1. File Gateway is for flat files only. You are not storing anything on premises. Files will store directly on S3
1. For a block base storage we have volume gateways they are two type of gateways first is stored volumes and the second is cached volumes
1. Stored volumes- entire dataset is stored on site and is asynchronously backed up to S3
1. Cached volumes- entire dataset is stored on S3 and only the most frequently access data is cached on site
1. Gateway virtual tape library (VTL) used for backup and uses popular backup application like netbackup, backup exec etc

## Snowball
1. Snowball is a Amazon device which is used to import and export huge amount of data to S3
1. Before snowball Amazon had a Legacy system which is called import export. where you send your own devices to Amazon to import or export data
1. For getting data from glacier using Snowball, You will get your data from glacier to S3 and then from S3 to snowball
1. There are two types of snowballs one is standard Snowboard which is normally 50 TB to 80 TB. Second is edge which have compute capabilities means you can run Lambda functions while transferring data. Third you have snow mobiles (truck with arm guards) currently in USA and this can have hundred of petabytes of data

## EC2 - Elastic Comppute Cloud
1. Pricing models is different for (on demand, spot, reserved and dedicated host) four instances
1. For spot instances if you terminate the instance you will pay for the complete hour and if Amazon will terminate your instance. When they will terminate your instance that hour will be free
1. EC2 instances types are Dr mc gift px
*. D stands for density
*. R stands for Ram
*. M stands for main
*. C stands for computer
*. G stands for graphics
*. I stands for IOPS
*. F stand for FPGA(Field programmable gate array) hardware acceleration
*. T stands for cheap general purpose think about T2 micro
*. P graphics (think about pictures) machine learning, bit coin mining 
*. X extreme memory
1. There are 5 type of EBS
*. SSD general purpose - GP2 - (up to 10,000 IOPS)(boot-able)
*. SSD provisioned IOPS -IO1- (more than 10000 IOPS)(boot-able)
*. HDD Throughput Optimized -ST1- frequently accessed workloads (sequence write, not boot-able)
*. HDD Cold -SC1 -Less frequently access data (not boot-able)
*. HDD Magnetic standard, cheap infrequently access data (boot-able)
1. You cannot Mount 1 EBS volume to multiple EC2 instances instead EFS
1. EC2 Termination protection is turned off by default you must turn it on
1. On an EBS backed instance the default action is for the root device volume to be deleted when the instance is terminated
1. EBS root volumes of your default AMI’s can’t be encrypted. You can use tired party tools like bit locker to encrypt the root volume, or this can be done when creating your own AMI’s from your existing EC2
1. Additional volumes can be encrypted
1. instance meta-data used to get information about an instance using http://169.254.169.254/latest/meta-data/

## Volumes & Snapshots
1. volumes exist on EBS (virtual hard disk)
1. snapshots exist on S3 (you can’t see them on S3)
1. snapshots are point in time copies of volumes
1. snapshots are incremental, meaning only blocks that have changed since your last snapshot are moved to S3. (cost saving)
1. To create a snapshot for EBS volumes that serve as root devices, you should stop the instance before taking the snapshot, however you can take a snapshot while the instance is running.
1. You can create AMI’s from both Volumes and snapshots
1. you can change EBS volume sizes on the fly, including changing the size and storage type.
1. Volumes will always be in the same availability zone as the EC2 instance
1. to move an Ec2 volume from one AZ/Region to another, take a snapshot or an image of it, then copy it (using copy command) to the new AZ/Region
1. snapshots of encrypted volumes are encrypted automatically
1. volumes restored from encrypted snapshots are encrypted automatically
1. you can share snapshots, but only if the are unencrypted. Only these snapshots can be shared with other AWS accounts or made public
1. AMI’s are regional. You can only launched and AMI from the region in which it is stored however you can copy AMI’s to other regions using the console, command line or the Amazon EC2 API

## EBS vs Instance Store
1. All AMI’s are categorized as either backed by Amazon EBS or backed by instance store
1. For EBS volumes: The root device for an instance launched from the AMI is an Amazon EBS volume created from an Amazon EBS snapshot
1. For instance store: The root device for an instance launched from the AMI is an instance store volume created from a template stored in Amazon S3. Thats why it is slow
1. Instance store volumes are sometime called ephemeral storage
1. Instance store volumes cannot be stopped if the underlying host fails you will lose your data
1. EBS backed instances can be stopped and you will not lose the data on this instance if it is stopped
1. You can reboot both and you will not lose your data
1. By default boot root volumes will be deleted on termination however with EBS volumes you can tell AWS to keep the root device volume

## EFS - Elastic File System
1. supports the network file system version 4 (NFSv4) protocol
1. You only pay for the storage you use. no pre-provisioning required
1. EFS can scale up to the peta-bytes
1. It can supports thousands of concurrent NFS connections
1. Data is stored across multiple AZ’s within a region
1. It have Read after write consistency
1. You can restrict users to on particular folders, great for file servers etc 

## ELB - Elastic Load Balancer
1. Instances monitored by ELBs are reported as ‘in service’ or ‘out of service’
1. Health checks, check the instance health by talking to it
1. ELB have their own DNS names. You are never given an IP address
1. Currently there are three type of ELBs (application, network and classic) 
1. To deploy an application Load Balancer you will need at least two public subnets

## Cloud-watch
1. Cloud watch is for performance monitoring. Standard monitoring is 5 minutes and detailed monitoring is 1 minute (extra cost)
1. You can create your own dashboard with cloud watch this will create awesome dashboards to see what is happening with your AWS environment
1. You can create alarms with your cloud watch that will allow you to set alarms and that notify you when particular threshold are hit (can use auto scaling)
1. Cloud watch events help you to respond to state change in your AWS resources for example you can watch the event of EC2 instance start up and you can hit Lambda function
1. Cloud watch logs help you to aggregate, monitor and store logs

## AWS  Lambda
1. Lambda is a computer service where you can upload your code and create a Lambda function. Lambda take care of provisioning and managing the servers that you used to run the code. You don't have to worry about operating systems, patching, scaling etc.
1. You can use lambda as an event driven computer service where lambda runs your code in response to events. These events could be changes to data in an Amazon S3 bucket or an Amazon dynamoDB table.
1. You can use it as a computer service to run your code in response to http requests using Amazon API Gateway or API calls made using AWS SDKs

## Route53
1. Alias records are same as CNAME records
1. Alias records allow you to resolve naked domain names or sometimes calls zone Apex records to AWS individual resources like elastic Load Balancer’s DNS address
1. When you are making request to route 53 you are going to be charged for the request If you are using CNAME if however you are making a request and it is an alias record you won't be charged by AWS
1. There are five different routing policies (simple, weighted, latency, fail-over and Geo-location)

## Databases
1. AWS offer RDS for OLTP On Line Transaction Processing (MSSQL, MYSQL, PostgreSQL, Oracle, Aurora, MariaDB), DynamoDB for no sql, Redshift for OLAP online analytics processing, Elasticache for in memory caching (memcached and Redis) and DMS for database migration service
1. Dynamo DB offer “push button” scaling, meaning that you can scale your database on the fly, without any downtime.
1. RDS Is not so easy, you usually have to use a bigger instance size or to add a read replica also it involve manual process not like push button

### Dynamo DB
1. Stored on SSD storage
1. Spread across three Graphically distinct data centers
1. It has eventually consistent reads (by default) and also offers strongly consistent reads
1. Eventually consistent reads: Consistency across all copies of data is usually reached within a second. Repeating a read after a short time should return the updated data
1. Strongly consistent read returns a result that reflects all writes that received a successful response prior to the read
1. A query operation finds item in a table using only primary key attribute values. You must provide a hash or partition key attribute name and a distinct value to search for
1. You can optionally provide a range or sort key attribute name and value, and use a comparison operator to refine the search result
1. By default, a query returns all the data attributes for items with the specified primary key(s); however, you can use the ProjectionExpression parameter so that the query only return some of the attributes, rather than all of them
1. Query results are always sorted by the range or sort key. If the data type of the range or sort key is number, the result are return in numaic order; otherwise, the result will return in order of ASCII character code values. By default, the sort order is ascending. To reverse the order set the ScanIndexForward parameter to false
1. In query by default every thing is eventually consistent but can be change to strongly consistent
1. You can have 5 local and and 5 global indexes per table
1. You can create local indexes only the time of table creation and you can create Global index whenever you want and you can also delete Global indexes
1. Scan operation examines every item in the table. By default, a scan return all the data attributes for every item; however, you can use the ProjectionExpression parameters so that it can only returns some of the attributes, rather than all of them
1. Read provisioned throughput (multiple of 4kb read and 2 read per second for eventual and 1 read per second for strongly consistent)
1. Write provisioned throughput (1kb write chunk and 1 read per second)
1. supports conditional writes are idempotent and atomic counters (increment or decrement) are not idempotent
1. If your application needs to read multiple items, you can use BatchGetItem API. A single BatchGetItem request can retrieve up to 1 MB of data, which can contains as many as 100 items. In addition, a single BatchGetItem request can retrieve items from multiple tables
1. To access dynamo DB with web identity provider, first user authenticates with ID provider such as Facebook. User will get a token from their ID providers. Then your code will calls AssumeRoleWithWebIdentity API and provides the provider’s token and specifies the ARN for the IAM role. Now application can access dynamo DB from between 15 minutes to 1 hour (default is 1 hour)
1. ProvisionedThroughputExceededException means you exceeded your maximum allowed provisioned throughput for a table or for one or more global secondary indexes.
1. You can increase “provisioned throughput limits” by raising a ticket to AWS support


### Aurora
1. Two copies of your data is contained in each availability zone, with minimum of three availability zones. Its means you will have 6 copies of your data
1. It is designed to transparent transparently handle the loss of up to 2 copies of data without affecting database write availability and up to 3 copies of data without affecting read availability
1. Aurora storage is also self healing. Data blocks and disks are continuously scanned for errors and repaired automatically
1. But this all doesn't mean that your Aurora will be highly available. you have to create another instance in another availability zone for highly available Aurora database solution
1. Aurora come with two different type of replicas. First is Aurora replica (you can have up to 15) second is mysql replica (up to 5) for Aurora

### Redshift
1. AWS data-ware house solution. Two different type of configurations
1. Single node configuration can be used for small to medium size businesses. It has 160 GB worth of storage
1. In multi node configuration first of all you have your leader node which manages client connections and receive queries. Then you have your compute nodes under your lead node. You can have up to 128 compute nodes. These nodes are used for store data and perform queries and computations

## Elasticache
1. Elastic cache is a web service that makes it easy to deploy, operate, and scale an in-memory cache in the cloud. The service improve the performance of web application by allowing you to retrieve information from fast, managed, in-memory cache, instead of relying entirely on slower disk-based databases. It supports two open source in-memory cache engines  (memcached and Redis)

## VPC - Virtual Private Cloud
1. Think of vpc as a logical data center in AWS
1. It consists of Internet gateways or virtual private Gateways, route tables, network access control list, subnets, and security groups
1. 1 subnet = 1 availability zone
1. Network access control list are stateless its means that you have to open port for inbound as well as for outbound
1. You can peer different VPS but transitive peering is not allowed
1. you cannot create a VPC peering connections between VPCs in different regions
1. you cannot create a VPC peering connections= between VPCs that have matching or overlapping CIDR blocks

### NAT instance
1. When creating a Nat instance, disable source/destination check on the instance
1. NAT instances must be in public subnet. There must be a route out of the private subnet to the Nat instance, in order for this to work
1. The amount of traffic that Nat instance can support depends on the instance size if you are bottlenecking, increase the instance size
1. You can create high availability using auto scaling groups, multiple subnets in different availability zones, and script to automate fail-over
1. NAT instances are behind security groups

### NAT gateways
1. Nat gateway is fully managed services, provided by AWS. It can automatically scale up to 10 Gbps. No need to patch them, not associated with any security group. A public IP address will automatically assigned to them
1. Always update your route tables after creating Nat Gateway and point them to your nat gateway
1. For creating redundancy you should have more than one NAT gateway in more than 1 subnet/ AZ
1. They for more secure than nat instances. You don't have any ssh access to nat gateways

### Network Access Control List
1. Your VPC automatically comes in a default network ACL, and by default it allow all the outbound and inbound traffic
1. You can create custom network ACL. By default, each custom network ACL denies all the inbound and outbound traffic until you add rules.
1. Each subnet in your vpc must be associated with a network ACL. If you don't explicitly associate a subnet with a network ACL, the subnet is automatically associated with the default network ACL.
1. You can associate a network ACL with multiple subnets; however, a subnet can be associated with only one network ACL at a time. When you associate a network ACL with a subnet, the previous association is removed
1. Network ACL can span in availability zones where subnet cannot
1. Network ACL contain a numbered list of rules that is evaluated in order, starting with the lowest number rule
1. Network ACLs have separate inbound and outbound rules, and each rule can either allow or deny traffic
1. Network ACL are stateless; responses to allowed inbound traffic are subject to the rules for outbound traffic and vice versa
1. Do remember to allow ephemeral ports on your outbound traffic in ACL e-g for web requests
1. You can block IP addresses using network ACLs not security groups

### VPC Flow log
1. You cannot enable flow logs for VPC that are peered with your vpc unless the peer VPC is in your account
1. You cannot tag a flow log
1. After you have created a flow log, you cannot change its configuration; for example you cannot associate a different IAM roll with the flow log
1. Not all IP traffic is monitored, e-g in following cases
1. Traffic generated by instances when they contact the Amazon DNS server. if you use your own DNS server then all traffic to that DNS server will by logged
1. Traffic generated by a Windows instance for Amazon Windows license activation will not be monitored
1. Traffic to and from 169.254.169.254 for instance is meta-data
1. DHCP traffic
1. Traffic to the reserved IP addresses for the default vpc router

## API Gateways
1. API gateways has caching capabilities to increase performance
1. API Gateway is low cost and scale automatically
1. You can throttle API gateway to prevent attacks
1. You can log results to cloud watch
1. If you are using JavaScript/AJAX that uses multiple domain with API Gateway ensure that you have enabled CORS on API gateway

## SQS - Simple Queue Service
1. A web service that gives you access to message queue that can be used to store messages while waiting for computer to process them
1. Queues can either be standard (default) or first in first out (in order)
1. It is a message oriented API
1. It is pull based, not pushed based
1. Messages are 256 KB in size
1. Messages can be kept in the queue from 1 minute to 14 days. The default is 4 days
1. Visibility time out is the amount of time that the message is invisible in the queue, after a reader pick up the message. Provided the job is processed before the visibility Timeout expired, the message will then be deleted from the queue. if the job is not processed within the time, the message will become visible again and another reader will process it. This could result in the same message being delivered twice(means you should solve duplicate messages problem by your self)
1. Visibility time out maximum is 12 hours
1. It guarantees that your messages will be processed at least once
1. Amazon SQS long polling is a way to retrieve messages from your Amazon SQS queues. while the regular short pulling returns immediately, even if the message you being pulled is empty, long polling does not return a response until a message arrived in the message queue, or the long poll times out
1. You need to implement your own application level tracking, especially if your application uses multiple queues.

## SWF - Simple Work Flow service
1. It has a retention period of up to 1 years for work flow execution
1. It presents a task-oriented API
1. It ensures that a task is assigned only once and it never duplicated
1. It keeps track of all the tasks and events in an application
1. It has three type of actors (starters, deciders and activity works)
1. Work flows starters can be a application that can initiate or start a work flow. e-g it can be e Commerce website placing an order or a mobile application searching for bus timetables
1. Deciders control the flow of activity task in a work flow execution. If something has finished in work flow or fail, decider decide what to do next
1. Activity workers carry out the activity tasks, can be human or machine

## SNS - Simple Notification Service
1. pushed based messages oriented service
1. subscribers can by HTTP, HTTPS, Email, email JSON, SQS, application or lambda function

## kinesis (stream, fire-hose and analytics)
1. data producers send data to stream (consist of shards) pip the data to consumers
1. fire-hose wont hold data, you can query data using lambda or send direct to S3
1. analytics sit on top and allow you to query data to stream or fire-hose

## Amazon Organization
1. Consolidated billing allows you to get volume discounts on all your accounts
1. unused reserved instances for the EC2 are applied across the group
1. cloud trail is on a per account and per region basis but can be aggregated in to a single bucket in the paying account
1. soft limit is 20 organization unit accounts, but can increase with request

## STS – Security Token Service
1. You can use three options, Federation (e-g active directory), Federation with Mobile and Cross account access
1. Can be divided into three component. Identity Broker (take identity from point A to B), Identity store (service like facebook or google) and Identities (user of service like facebook)
1. Sequence will be, your developed identity broker (calls the new GetFederationToken function using IAM credentials. The call must include an IAM policy and a duration (1 to 36 hours), along with a policy that specifies the permissions to be granted to the temporary security credentials) communicate with LDAP and STS (return access key, a secret access key, a token and duration)
1. application gets temporary access to AWS resources (uses IAM to verify the credentials allow the requested operation) 
