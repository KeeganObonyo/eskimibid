val akkaVersion   = "2.6.5"
lazy val sharedSettings = Seq(
  organization  := "com.eskimi",
  version       := "0.1.0",
  scalaVersion  := "2.12.11",
  resolvers ++= Seq(
    "rediscala" at "https://dl.bintray.com/etaty/maven",
    "Typesafe repository releases" at "https://repo.typesafe.com/typesafe/releases/"
  ),
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked"
  ),
  test in assembly := {},
  updateOptions := updateOptions.value.withLatestSnapshots(false),
  assemblyMergeStrategy in assembly := {
   case PathList("META-INF", xs @ _*) => MergeStrategy.discard
   case x => MergeStrategy.first
  }
)

lazy val piglet = (project in file("."))
  .aggregate(bid, campaigns, core, web)

lazy val core = (project in file("core")).
  settings(
    sharedSettings,
    libraryDependencies ++= Seq(
      "com.typesafe.akka"             %%  "akka-actor"          % akkaVersion,
      "com.typesafe.akka"             %%  "akka-slf4j"          % akkaVersion,
      "com.typesafe.akka"             %%  "akka-http"           % "10.0.4",
      "ch.qos.logback"                %   "logback-core"        % "1.1.9",
      "ch.qos.logback"                %   "logback-classic"     % "1.1.9",
      "com.typesafe"                  %   "config"              % "1.3.1",
      "net.jpountz.lz4"               %   "lz4"                 % "1.3.0",
      "commons-daemon"                %   "commons-daemon"      % "1.0.15",
      "com.github.mauricio"           %   "mysql-async_2.12"    % "0.2.21",
      "com.typesafe.akka"             %%  "akka-testkit"        % akkaVersion   % "test",
      "org.scalatest"                 %   "scalatest_2.12"      % "3.0.1"       % "test"
    )
  )

lazy val campaigns = (project in file("campaigns")).
  settings(
    sharedSettings,
    libraryDependencies ++= Seq(
      "com.typesafe.akka"          %% "akka-testkit"   % akkaVersion   % "test",
      "org.scalatest"              %  "scalatest_2.12" % "3.0.1"       % "test",
    )
  ).dependsOn(core)

lazy val bid = (project in file("bid")).
  settings(
    sharedSettings,
    libraryDependencies ++= Seq(
      "com.typesafe.akka"          %% "akka-testkit"   % akkaVersion   % "test",
      "org.scalatest"              %  "scalatest_2.12" % "3.0.1"       % "test",
    )
  ).dependsOn(campaigns, core)

lazy val web = (project in file("web")).
  settings(
    sharedSettings,
    libraryDependencies ++= Seq(
      "com.typesafe.akka"  %% "akka-testkit"           % akkaVersion  % "test",
      "org.scalatest"      %  "scalatest_2.12"         % "3.0.1"      % "test",
      "com.typesafe.akka"  %  "akka-http-testkit_2.12" % "10.0.3"     % "test"
    )
  ).dependsOn(campaigns, bid, core)
