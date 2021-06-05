

var db = openDatabase('chatdb', '1.0', 'save chat data DB', 2 * 1024 * 1024);
db.transaction(function (tx) {
    // 已读消息
    // 未读消息
    tx.executeSql('CREATE TABLE IF NOT EXISTS MESSAGES_READED (id unique, log)');
    tx.executeSql('CREATE TABLE IF NOT EXISTS MESSAGES_UNREADED (id unique, log)');
    // 联系人 联系群
    tx.executeSql('CREATE TABLE IF NOT EXISTS CONTACT (id unique, log)');
    // 群组成员
    tx.executeSql('CREATE TABLE IF NOT EXISTS CONTACT (id unique, log)');
});

db.transaction(function (tx) {
    tx.executeSql('SELECT * FROM MESSAGES', [], function (tx, results) {
        let len = results.rows.length, i;
        msg = "<p>查询记录条数: " + len + "</p>";
        document.querySelector('#status').innerHTML +=  msg;

        for (i = 0; i < len; i++){
            alert(results.rows.item(i).log );
        }

    }, null);
});