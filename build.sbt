name := "play-shiro"

version := "1.1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  evolutions,
  jdbc,
  "com.typesafe.play" %% "anorm"      % "2.4.0",
  "org.mindrot"        % "jbcrypt"    % "0.3m",
  "org.apache.shiro"   % "shiro-core" % "1.2.5",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
