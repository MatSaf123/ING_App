# Profile Display App

## PYTANIA
\-

### SCREENY

   
<img src = "https://camo.githubusercontent.com/0aeb5a35ad2af9c1201d7edc24b0e8cd3ae7dbde/68747470733a2f2f692e696d6775722e636f6d2f6b4a4b4b41724e2e6a7067"
data-canonical-src = "https://i.imgur.com/kJKKArN.jpg" width="331" height="700" />
<img src = "https://camo.githubusercontent.com/64005edf6e8b22c989ff9c7aea149a2fcbf28159/68747470733a2f2f692e696d6775722e636f6d2f476d524d4752382e6a7067"
data-canonical-src = "https://i.imgur.com/GmRMGR8.jpg" width="331" height="700" />  
<img src = "https://camo.githubusercontent.com/3498c50eb7fd4ad49f2557daa80b488514748fc0/68747470733a2f2f692e696d6775722e636f6d2f50347431745a4f2e6a7067"
data-canonical-src = "https://i.imgur.com/P4t1tZO.jpg" width="331" height="700" />   



#### OBECNY ETAP PROJEKTU

Działa wyświetlania wszystkich postów - nazwy użytkownika który je zapostował, tytuł posta, treść(ciało) posta oraz liczba komentarzy dla danego posta.

Po kliknięciu w nazwę autora postu przenosi się do fragmentu wyświetlającego nazwę użytkownika, e-mail, stronę internetową, ulicę, miasto, kod pocztowy, szerokość i długość geograficzną, fragment mapy (Google Maps) z zaznaczoną lokalizacją użytkownika.

W przypadku błędu przy próbie pobrania danych z API, aplikacja wyrzuca komunikat o błędzie.

TODO:
- [x] Sprawienie aby klikalna była nazwa użytkownika, nie cały post
- [x] Dodanie fragmentu z informacjami o użytkowniku 
- [x] Przerobienie wartości długości i szerokości geograficznej na widok z Google Maps
- [x] Dodanie fragmentu na komentarze
- [ ] Poprawienie działania i wyglądu fragmentu na komentarze
- [ ] Dodanie fragmentu na zdjęcia użytkownika + połączenie go z guzikiem w profilu użytkownika
- [ ] Dodanie menu z informacji o autorach + informacji o źródle assetów z internetu
- [ ] Dodanie testów
- [ ] Stronicowanie postów
- [ ] Rozszerzenie obsługi wyjątków
- [ ] Ogólne prace związane z poprawą wyglądu aplikacji

<br /><hr /><br />


Project consisting of displaying various information from API - https://jsonplaceholder.typicode.com/

Project designed for mobile devices, written in Kotlin.
We use Retrofit for making API calls, Moshi for deserializing JSON objects to Kotlin data objects and data binding.

Contributors:
1. MatSaf123
2. TymJannek
3. Shepard701

