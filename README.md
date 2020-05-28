# Profile Display App

## SCREENY
<img src = "https://github.com/MatSaf123/ING_App/blob/master/app_gif.gif"
data-canonical-src = "https://github.com/MatSaf123/ING_App/blob/master/app_gif.gif" width="331" height="700" />
   
<img src = "https://i.imgur.com/KTYaZUc.jpg"
data-canonical-src = "https://i.imgur.com/KTYaZUc.jpg" width="331" height="700" />
<img src = "https://i.imgur.com/EpgQAKQ.jpg"
data-canonical-src = "https://i.imgur.com/EpgQAKQ.jpg" width="331" height="700" />
<img src = "https://i.imgur.com/sTNj3iN.jpg"
data-canonical-src = "https://i.imgur.com/sTNj3iN.jpg" width="331" height="700" /> 
<img src = "https://i.imgur.com/SGdNhPU.jpg"
data-canonical-src = "https://i.imgur.com/SGdNhPU.jpg" width="331" height="700" />
<img src = "https://i.imgur.com/Tnntv9s.jpg"
data-canonical-src = "https://i.imgur.com/Tnntv9s.jpg" width="331" height="700" />
   


### OBECNY ETAP PROJEKTU

Działa wyświetlanie wszystkich postów - nazwy użytkownika który je zapostował, tytuł posta, treść(ciało) posta oraz liczba komentarzy dla danego posta.

Po kliknięciu w nazwę autora postu przenosi się do fragmentu wyświetlającego nazwę użytkownika, e-mail, stronę internetową, ulicę, miasto, kod pocztowy, fragment mapy (Google Maps) z zaznaczoną lokalizacją użytkownika. Po kliknięciu w napis 'Photos' aplikacja przenosi do fragmentu ze zdjęciami użytkownika.

Po kliknięciu w napis 'Comments' aplikacja przenosi do wszystkich komentarzy danego posta.

W przypadku błędu przy próbie pobrania danych z API, aplikacja wyrzuca komunikat o błędzie.

TODO:
- [x] Sprawienie aby klikalna była nazwa użytkownika, nie cały post
- [x] Dodanie fragmentu z informacjami o użytkowniku 
- [x] Przerobienie wartości długości i szerokości geograficznej na widok z Google Maps
- [x] Dodanie fragmentu na komentarze
- [x] Dodanie fragmentu na zdjęcia użytkownika + połączenie go z guzikiem w profilu użytkownika
- [x] Dodanie menu z informacji o autorach + informacji o źródle assetów z internetu
- [ ] Dodanie testów
- [x] Stronicowanie postów*
- [ ] Rozszerzenie obsługi wyjątków
- [x] Ogólne prace związane z poprawą wyglądu aplikacji

* implementacja stronicowania postów okazała się problematyczna ze względu na długie czasy oczekiwań przy żądaniach do REST API, z tego powodu zaimplementowana jest jej wersja okrojona i uproszczona.

<br /><hr /><br />


Project consisting of displaying various information from API - https://jsonplaceholder.typicode.com/

Project designed for mobile devices, written in Kotlin.
We use Retrofit for making API calls, Moshi for deserializing JSON objects to Kotlin data objects and data binding.

Contributors:
1. MatSaf123
2. TymJannek
3. Shepard701
