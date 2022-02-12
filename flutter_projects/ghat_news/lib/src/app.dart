import 'package:flutter/material.dart';
import 'screens/news_list.dart';
import 'blocs/stories_provider.dart';
import '../src/resources/news_db_provider.dart';


class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    newsDbProvider.init();
    print(newsDbProvider.db);
    return StoriesProvider(
      child: MaterialApp(
        title: 'Hacker News news',
        home: NewsList(),
      ),
    );
  }
}

