ENGLİSH / Türkçe


ENGLISH DESCRIPTION:
This project is a Spring Boot application designed for team communication scenarios within a company. First, users register and log into the system based on their rank. In this section, users encounter a team entry tab. If the person is a team leader, they can create a new team with the "Create Team" button. Of course, this can only happen if they don't already belong to a team. Otherwise, if the person is an engineer or an intern, they only see a list of existing teams. These individuals need to be added to the team of the team leader. If the person already belongs to a team, they are redirected to a homepage for each team separately using the "Go to Team" button. Explaining the functions of the homepage:

Assign Task:
Users can assign tasks to those with lower ranks.

My Tasks:
Users can use functions to manage their active tasks, completed tasks, and tasks assigned to others if feedback has been provided. (Feedback is not visible to interns.)

Start Meeting: (ONLY VISIBLE TO TEAM LEADERS)
Sends a notification about starting a meeting on the date specified by the team leader. This notification can be sent to interns, engineers, or both.

User Operations: (ONLY VISIBLE TO TEAM LEADERS)
Team leaders can add users to their team. Similarly, they can remove a user from their team.

ALERTS:
Opens notifications.

USER PROFILE:
Users can see their rank and the number of tasks they have completed.

In addition to these, on the homepage, we can have a live chat for team communication and a list of users in the team.

!!! In the frontend part of the project, Turkish characters are utilized for text elements such as task assignments, user operations, and alerts. !!!


TÜRKÇE AÇIKLAMA:
Bu proje bir şirkette takım içi iletişim senaryosu için tasarlanmış spring boot projesidir. İlk olarak kullanıcı rütbesine göre kayıt olur ve sisteme giriş yapar. Bu kısımda kullanıcıyı bir takım giriş sekmesi karşılar. Bu sekmede eğer kişi bir takım lideri ise takım oluştur butonu ile yeni bir takım oluşturabilir. Tabi bu herhangi bir takımı yoksa gerçekleşebilir. Onun dışında eğer kişi mühendis veya stajer ise sadece mevcut takımların bir listesini görüntüler. Bu kişileri takım lideri'nin takımına alması gerekmektedir. Kişinin bir takımı var ise takıma git tuşu ile her takımın ayrı olacak şekilde bir ana sayfaya yönlendirilir. Ana sayfa işlevlerini açıklayacak olursak :

Görev Ata:
Kullanıcı rütbesi kendisinden düşük olan kişilere bir görev atayabilir.

Görevlerim:
Kullanıcı kendi aktif görevlerini, tamamlanmış görevlerini ve başkasına verdiği görevler ona geri dönüş yapıldıysa bunun kontrolünü sağlayan işlevleri kullanabilir. (Geri dönüş stajerde görünmemektedir.)

Toplantı Başlat: (SADECE TAKIM LİDERİ GÖREBİLİR)
Takım liderinin belirlediği tarihte bir toplantı başlatacağı hakkında bildirim gönderir. Bu bildirimi stajerler , muhendisler veya her ikisi içinde gönderebilir.

Kullanıcı İşlemleri:(SADECE TAKIM LİDERİ GÖREBİLİR)
Takım lideri takımına kullanıcı ekleyebilir. Aynı şekilde takımında olan bir kullanıcıyı çıkarabilir.

ZİL ZEMBOLÜ:
Bildirimleri açar.

KULLANICI PROFİLİ:
Kişi rütbesini ve tamamladığı görev sayısını görebilir.

Bunlar dışında ana sayfada takım içi iletişimin sağlanabileceği bir canlı sohbet ve takımda bulunan kullanıcıların bir listesini görebiliriz.
