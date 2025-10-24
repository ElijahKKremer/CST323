# CST323
CST323 Repository

# Activity Report: Test Application Development and Cloud Deployment

---

## 1. Cover Sheet
 #### Test Application Development and Cloud Deployment  
 #### Elijah Kremer 
 #### October 24, 2025  
 #### Cloud Computing Activity Report  
 #### CST 323

---

## 2. Screenshot of Azure Portal
*Insert screenshot here of being logged into the Azure Portal.*  
![Azure Portal Screenshot](./src/images/azureportal.png)

## Screenshot of Application
![Application](./src/images/Application.png)
---

## 3. Framework and Technology Chosen
The test application is being developed using the following stack:  
- **Backend Framework:** Spring Boot (Java)  
- **Frontend Framework:** Bootstrap with Angular for UI components  
- **Database:** MySQL (hosted in Azure Database for MySQL)  
- **Cloud Provider:** Microsoft Azure  
- **Version Control:** GitHub for repository management  

This combination was chosen for its scalability, modularity, and strong community support.

---

## 4. Database Progress and Status
### ER Diagram 
### Users Table

| Field     | Type      | Key |
|-----------|-----------|-----|
| user_id   | INT       | PK  |
| email     | VARCHAR   |     |
| full_name | VARCHAR   |     |
| role      | VARCHAR   |     |

### Products Tabel

| Column       | Type          | Key        |                           |
|--------------|---------------|--------------------|--------------------------------------|
| id           | BIGINT        | PK, AUTO_INCREMENT | 
| name         | VARCHAR(100)  | NOT NULL           |   |
| description  | VARCHAR(255)  | NULL allowed       | 
| price        | DECIMAL(10,2) | NOT NULL           |

--- 

### Tables Built
- **Users** (user_id, username, email, password, role)  
- **Products** (product_id, name, description, price, stock)  

 

### Tables Remaining
- **Payments** (payment_id, order_id, amount, status, payment_date)  
- **AuditLogs** (log_id, user_id, action, timestamp)  
- **OtherPages** Potential for more pages included
- **Orders** (order_id, user_id, product_id, quantity, order_date)  



---

## 5. Application Development Progress
### Pages/Services Built
- **Dashboard Page** displaying user-specific data  
- **Products Page** displays products


### Pages/Services Remaining
- **Login Page** with authentication service  
- **Payment Page** with integration to payment gateway  
- **Admin Panel** for managing users and products  
- **Audit Log Service** for tracking user actions  
- **Other**
---

## 6. Current Issues
- **Database Connection Errors:** Intermittent issues connecting Spring Boot to Azure MySQL.  
- **Deployment Pipeline:** CI/CD pipeline not fully automated; manual steps still required.  
- **UI Responsiveness:** Some Bootstrap components not rendering correctly on mobile devices.  
- **Scaling Tests:** Auto-scaling configuration in Azure App Service needs refinement.  

---

## 7. Screencast Demonstration
*Insert screencast URL here.*  
[Screencast Demo ](https://www.loom.com/share/93825817ad484a4eaa5e78dee8044fc5)

---

## 8. Cloud Computing Research Questions

Cloud computing has gone through several stages of evolution, beginning with mainframes and client–server models that required heavy investment in physical infrastructure. In the early 2000s, virtualization allowed multiple applications to run on shared hardware, paving the way for Infrastructure‑as‑a‑Service (IaaS). This innovation reduced costs and improved efficiency by pooling resources. Over time, Platform‑as‑a‑Service (PaaS) and Software‑as‑a‑Service (SaaS) emerged, enabling developers and businesses to deploy applications faster without managing hardware. Today, cloud computing powers artificial intelligence, big data, mobile apps, and enterprise systems, offering scalability, flexibility, and cost efficiency that traditional on‑premises solutions cannot match.

Netflix’s shift from on‑premises data centers to Amazon Web Services (AWS) demonstrates how an established company can leverage the cloud to solve scalability challenges. By 2010, much of its customer traffic was already running on AWS, and by 2013, Netflix aimed to have nearly all services in the cloud.
Advantages:
- Scalability: Netflix could auto‑scale resources to handle unpredictable spikes in streaming demand.
- Cost efficiency: The company avoided over‑purchasing hardware and only paid for resources consumed.
- Focus on innovation: Engineers concentrated on improving the streaming platform rather than managing infrastructure.

Disadvantages:
- Dependency on AWS: Reliance on a third‑party provider created risks if outages occurred.
- Loss of control: Netflix had less direct oversight of IT resources compared to on‑premises.
- Security concerns: Storing massive amounts of customer data in the cloud increased exposure to potential breaches.
Cloud features leveraged:
- On‑demand elasticity to instantly scale resources.
- Horizontally scalable architecture for rapid growth.
- Shared resource pools for compute, storage, and networking.

---
I recommend using cloud computing to deploy business applications. In the Chapter 1 prototyping example, developers were able to launch multiple virtual servers in minutes at a cost of only a few dollars per day. By contrast, the on‑premises approach required purchasing three servers at $3,000–$5,000 each, plus licensing, shipping, and installation, with a timeline of one to three months before testing could even begin. Cloud computing eliminates these delays and upfront costs, while offering the flexibility to scale resources up or down as needed. Virtual machines can be discarded easily, reducing waste and financial risk. Most importantly, the cloud enables teams to focus on building and improving applications rather than managing hardware, making it the more efficient, cost‑effective, and sustainable solution for modern businesses.




---
