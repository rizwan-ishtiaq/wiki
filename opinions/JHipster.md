> Q: What do you think of JHipster for developing REST API at EMBL-EBI?

Yo [Jhipster](https://www.jhipster.tech/),

It is nothing but a scaffolding code/project/deployment generator tool (based on yeoman and Liquibase) mainly for handling the configuration complexity for web apps (which include front-end / back-end) and micro-services. Yes Jie, you are right it is using spring-boot under the hood for server side logic.

Micro services architecture is good for very large teams, at EBI we are small teams where every team member is working on individual project. According to my little information/knowledge, we at EBI are not planing for micro services because mini individual REST APIs will fulfill the need. Our huge services focuses on data but not much complex (configuration) as we don't need to provide security(user authentication/authorization) or social media support etc

As far as REST API concern we should use simple maven + spring boot combination. For that purpose simple scaffolding tool like [spring initializr](start.spring.io) will enough. JHipster is a great tool, developer should use it locally to create projects (If they are not able to figure out which dependencies should use and how different framework/dependencies/libraries interact/glue together). But the project generated using JHipster should not be directly added to repository (It is same like adding eclipse/Intellij files into code repository) due to following reasons
1. To keep code simple and clean
1. Have more control on project structure
1. Avoid over kill (e-g extra libraries/jars added by JHipster in background)
1. In some same cases, Jhipster will coupled it self with application source code (like spring profile Constance etc)
1. At EBI we already have deployment procedures in place, so we can't take benefit from Jhipster in this regard
1. Avoid learning curve of jhipster


Having said above, we should consider JHipster for new uniprot website because all above point will benefits website plus due to following
1. Out of the box support for bootstrap, progressive web app, Ionic and Angular or react
1. Opinionated Approach (structure in place)
1. Quickly production ready (minimum configurations)
1. leverage build-in swager,wepack support and many more

[Quick-start-link](https://start.jhipster.tech/)
