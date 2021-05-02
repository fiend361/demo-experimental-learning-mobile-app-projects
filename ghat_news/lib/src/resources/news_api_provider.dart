
import 'package:ghat_news/src/models/item_model.dart';
import 'package:http/http.dart' show Client;
import 'dart:convert';
import 'dart:async';
import '../models/item_model.dart';
import 'repository.dart';


final _root = 'https://hacker-news.firebaseio.com/v0';

class NewsApiProvider implements Source {
  Client client = new Client();

  Future<List<int>> fetchTopIds() async {
    var url = Uri.parse('$_root/topstories.json');
    final response = await client.get(url);

    final ids = json.decode(response.body);

    return ids.cast<int>();
  }

  Future<ItemModel> fetchItem(int id) async {
    var url = Uri.parse('$_root/item/$id.json');
    final response = await client.get(url);
    final parsedJson = json.decode(response.body);

    return ItemModel.fromJson(parsedJson);
  }
}





