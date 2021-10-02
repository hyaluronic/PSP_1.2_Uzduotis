Repository for Software system design course.

1. Ar buvo aiškus ir patogus unit testai, ar kodas aiškus:
Kodas aiškus, testais naudotis buvo patogu, išskyrus dviem, kurie nori validuot pagal taisyklę, kurios validatorius nežino.
	
2. Kaip jus galėtumete juos pagerinti:
Tuos du testus pažymėjau anotacija @Ignore ir pakeičiau savais atskirame faile (nes neleido daryti githubas PR).
Testai turėtų aprėpti daugiau kritinius ar ribinius atvėjus.
Testai turėtu būti skirtinguose Test klasėse: viena PhoneCheker'iui, kita EmailChecker'iui ir viena PasswordChecker'iui (Taip kaip yra su pačiais Checker'iais), nes visi Checker'iai aprėpia skirtingas logines sritis, vieni validuoja TIK password'us, kiti tik Email'us...
Daryčiau viska su vienu public metodu kiekvienam Checker'iui (validate) ir naudočiau Exceptionus klaidos išgavimui.

3. Kokius unit testus jus galėtumėte pridėti (jei tokių yra):
Daugiau ribinius atvėjus aprėpiančiu testų. Testų, kurie pravaliduotu ne vieną taisyklę, o daugiau ar visas validatoriaus taisykles, arba jog vienas testas atliktu kelis skirtingus assert'us tam pačiam metodui.
Keleta testų pridėjau savo sukurtuose "My<...>Test" failuose.
