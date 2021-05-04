import PartnerAPI from '@/core/api/gamming/Partner'

/*
服务类，某一个模块对象的所有相关方法。
返回值都为promise。所以需要前端自己then解决问题
正常且成功情况下，需要调用.then 自己在方法体内赋值
*/
class GameService {

    async allInfo() {
        return PartnerAPI.partnerInfoOfTenant()
    }

    //获取所有大类
    async getAllGamming() {
        return this.allInfo().then(response => {
            return Promise.resolve(this.getAllGammingType(response))
        })
    }

    //获取所有渠道
    async allChannel() {
        return this.allInfo().then(response => {
            return Promise.resolve(this.getAllChannel(response))
        })
    }

    //获取渠道下的所有分类
    async getAllType(gammingTypeId, channelId) {
        return this.allInfo().then(response => {
            return Promise.resolve(this.getGammeTypeInGammingTypeAndChannelId(response, gammingTypeId, channelId))
        })
    }

    // 获取渠道下的所有游戏
    async getAllGame(gammingTypeId, channelId) {
        return this.allInfo().then(response => {
            return Promise.resolve(this.getGameInGammingTypeAndChannelId(response, gammingTypeId, channelId))
        })
    }

    //获取所有游戏大类
    getAllGammingType(data) {
        var list = []
        for (var index in data) {
            list.push(data[index])
        }
        return list;
    }

    //获取所有平台
    getAllChannel(data) {
        var list = []
        var allGammingType = this.getAllGammingType(data);
        for (var index in allGammingType) {
            for (var channelIndex in allGammingType[index]["channels"]) {
                list.push(allGammingType[index]["channels"][channelIndex])
            }
        }
        return list;
    }

    //获取所有游戏类型
    getAllGammeType(data) {
        var list = []
        var allChannel = this.getAllChannel(data)
        for (var index in allChannel) {
            for (var gameTypeIndex in allChannel[index]["gameTypes"]) {
                list.push(allChannel[index]["gameTypes"][gameTypeIndex])
            }
        }
        return list;
    }

    //获取所有游戏类型
    getAllGamme(data) {
        var list = []
        var allGameType = this.getAllGammeType(data)
        for (var index in allGameType) {
            for (var gameIndex in allGameType[index]["games"]) {
                list.push(allGameType[index]["games"][gameIndex])
            }
        }
        return list;
    }

    //指定游戏大类的所有平台
    getChannelInGammingType(data, gammingTypeId) {
        var list = []
        var allChannel = this.getAllChannel(data)
        for (var channelIndex in allChannel) {
            var channel = allChannel[channelIndex]
            if (channel["gammingTypeId"] == gammingTypeId) {
                list.push(channel)
            }
        }
        return list
    }

    //指定游戏大类 指定游戏渠道的所有分类
    getGammeTypeInGammingTypeAndTenantChannelId(data, gammingTypeId, tenantChannelId) {
        var list = []
        var allChannel = this.getChannelInGammingType(data, gammingTypeId)
        for (var channelIndex in allChannel) {
            var channel = allChannel[channelIndex]
            if (channel["id"] == tenantChannelId) {
                for (var gameTypeIndex in channel["gameTypes"]) {
                    list.push(channel["gameTypes"][gameTypeIndex])
                }
            }
        }
        return list
    }

    //指定游戏大类 指定游戏渠道的所有游戏
    getChannelInGammingTypeAndTenantChannelId(data, gammingTypeId, tenantChannelId) {
        var list = []
        var allGameTypes = this.getGammeTypeInGammingTypeAndTenantChannelId(data, gammingTypeId, tenantChannelId)
        for (var gameTypeIndex in allGameTypes) {
            var gameType = allGameTypes[gameTypeIndex]
            for (var gameIndex in gameType["games"]) {
                list.push(gameType["games"][gameIndex])
            }
        }
        return list
    }

    //指定游戏大类 指定游戏渠道的所有分类
    getGammeTypeInGammingTypeAndChannelId(data, gammingTypeId, channelId) {
        var list = []
        var allChannel = this.getChannelInGammingType(data, gammingTypeId)
        for (var channelIndex in allChannel) {
            var channel = allChannel[channelIndex]
            if (channel["channelId"] == channelId) {
                for (var gameTypeIndex in channel["gameTypes"]) {
                    list.push(channel["gameTypes"][gameTypeIndex])
                }
            }
        }
        return list
    }

    //指定游戏大类 指定游戏渠道的所有游戏
    getGameInGammingTypeAndChannelId(data, gammingTypeId, channelId) {
        var list = []
        var allGameTypes = this.getGammeTypeInGammingTypeAndChannelId(data, gammingTypeId, channelId)
        for (var gameTypeIndex in allGameTypes) {
            var gameType = allGameTypes[gameTypeIndex]
            for (var gameIndex in gameType["games"]) {
                list.push(gameType["games"][gameIndex])
            }
        }
        return list
    }

}

const gameService = new GameService()

export default gameService
