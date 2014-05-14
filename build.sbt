name := "newInvoice"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.2",
  "org.webjars" % "bootstrap" % "3.1.1",
  javaJdbc,
  javaEbean,
  javaJpa,
  cache,
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final"
)

play.Project.playJavaSettings
