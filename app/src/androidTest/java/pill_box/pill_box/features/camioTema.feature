Feature: CambiarTemaClaroOscuro
  Scenario: cambia a oscuro 
    Given un boton switch para cambiar el tema
     When se pulse el boton 
     And la aplicaion este en modo claro
     Then la aplicacion estara en modo oscuro

