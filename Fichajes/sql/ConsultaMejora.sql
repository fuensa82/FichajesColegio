select idFichaje, currentTime 
from fichajes 
where idProfesor='331' #and fecha='20210927' #AND curso='2021-2022'
ORDER BY idFichaje DESC LIMIT 1