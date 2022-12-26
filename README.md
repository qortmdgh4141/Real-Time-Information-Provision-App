## 📹 Real-time_Information_Provision_App
- _The PDF file is a paper of Intelligent CCTV for Port Safety published by KIPS(Korean Information Processing Society)._ <br/> <br/> <br/> 

### 1. &nbsp; Background of Development <br/> 
- _Recently, CCTV-related technologies have been used in various ways in our daily lives, such as security and safety accident prevention. However, traditional CCTV cameras record more unnecessary information than is needed when a problem occurs. In addition, it is difficult to fully recognize and judge the site only with existing CCTV cameras. The Korea Safety and Health Agency announced that six deaths occurred every year at domestic ports during 2011-2021. It shows the limitations that existing CCTV cameras cannot solve safety accidents and human casualties in domestic ports. In order to solve these limitations, I devised "Intelligent CCTV for Port Safety & Real-Time Information Provision System" that can quickly and accurately respond to dangerous situations while checking the site in real time._ <br/><br/><br/>

### 2. &nbsp; Project Introduction <br/> 

- _The project used a variety of object detection and action recognition models based on traditional computer vision technique and deep neural network._ <br/><br/>

- _Based on the various deep learning models mentioned above, the following algorithms are implemented :_ <br/>
  
  - _Region of Interest (ROI)_ <br/>
    - _Regions of interest (ROI) means the meaningful and important regions in the images._ <br/>
    - _The ROI algorithm is implemented based on the binary mask technique in the image processing field._ <br/>
    - _In the mask image, pixels that belong to the ROI are set to 1 and pixels outside the ROI are set to 0._ <br/> 
    
  - _DeepSort_
    - _DeepSort is the tracking algorithm which tracks objects not only based on the velocity and motion of the object but also the appearance of the object._ <br/>
    
  - _Measuring Distance Between Objects_ <br/>
    - _The algorithm detects and analyzes low-risk and high-risk state by measuring the distance between the bounding box centers of objects._ <br/>
    - _When measuring distance, measure the distance between all objects detected in the image by reflecting the features of the 'complete graph'._ <br/>

  - _Time Series Data Analysis_ <br/>
    - _This algorithm detects and analyzes time series data using a queue, and its contents are as follows :_ <br/>
    
      1. _If the data recognized through the action recognition model is judged to be abnormal behavior, the penalty score is sequentially stored in the queue, which is a linear data structure. (Conversely, if the recognized data is judged to be normal behavior, the adventure score  is stored.)_ <br/>
      2. _At the same time, scores, which are time series data previously stored in the queue, are deleted from the queue by the FIFO (First In First Out) method of the queue._ <br/>    
      3. _By analyzing the sum of the scores in the queue in real time, if the value is an outlier, it is judged that it is currently a very dangerous situation_.<br/><br/>
  
- _Through the algorithms mentioned above, the following events are detected and analyzed :_ <br/>

  - _Intrusion and Loitering_
  - _Risk of Access to Restricted Areas_
  - _Risk of Collision Between Workers_
  - _Risk of Not Wearing Worker Safety Equipment_
  - _Fire Accident_
  - _Act of Smoking_
  - _Act of Falling_
  - _Act of Violence_ <br/><br/>

- _The analyzed information is stored in the database, and the administrator can quickly identify the current situation through text and graph-type information provided in real time._ <br/>

  - _The information can be checked not only on PC but also on smartphone._ <br/><br/>

- _If these functions are applied to the integrated control center in the port, it will be possible to build an intelligent infrastructure that can efficiently manage and operate. In other words, it can contribute to the introduction of a smart port monitoring system in the future._ <br/><br/><br/>

### 3. &nbsp; Real-Time Information Provision System <br/><br/>

  <img src="https://github.com/qortmdgh4141/Intelligent_CCTV_for_Port_Safety/blob/main/image/real-time_information_provision_system.png?raw=true"  width="560"> <br/>

  - _Since safety accidents occur at unexpected moments, it is important to check the site in real time and take prompt action._
    
  - _Therefore, this project provides an information provision system that allows managers to check the site in real time._ <br/> 
  
  - _First, after analyzing image data photographed by the CCTV camera, the following information is stored in a database._ <br/> 
    - Image of the On-Site 
    - The Number of People at the On-Site  
    - Safety Numerical Values at the On-site
    - Identification Number of People at the On-Site
    - Type of Event
    - Occurrence Time of Event
    - Warning and Danger Stage of Event
  
  - _Then, the information stored in the database is provided to the administrator's PC monitor or application in the form of text, image, graph, etc._ <br/>
    - _You can check the source code and information of the application in the following repository: <br/> https://github.com/qortmdgh4141/Real-time_Information_Provision_App.git_<br/>
    
  - _Through this, administrator can check the situation in real time anywhere, not limited to places, and respond quickly to problems in the site._ <br/> <br/> <br/>
