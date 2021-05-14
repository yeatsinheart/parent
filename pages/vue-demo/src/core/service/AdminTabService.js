/*
服务类，某一个模块对象的所有相关方法。
返回值都为promise。所以需要前端自己then解决问题
正常且成功情况下，需要调用.then 自己在方法体内赋值
*/
import store from '@/store'

class AdminTabService {
    tabid = 0;

    open(name, url) {
        this.tabid++;
        let newTab = {'id': this.tabid, 'name': name, "url": url};
        let isIn = store.state.tagsList.some(item => {
            //判断标签是否存在
            return item.id === newTab.id;
        });
        //不存在
        if (!isIn) {
            // 判断当前的标签个数
            if (store.state.tagsList.length >= 10) {
                // messages("warning", "当标签大于10个，请关闭后再打开");
            } else {
                store.state.tagsList.push(newTab);
                this.activeTab(newTab);
            }
        }
    }

    activeTab(tag) {
        store.state.tagActive = tag;
    }

    isActive(id) {
        if (store.state.tagActive) {
            return id === store.state.tagActive.id;
        }
        return false;
    }

    // 关闭其他
    onlyKeepThis(index) {
        let total = store.state.tagsList.length - 1;
        if (index !== total) {
            if (index !== 0) {
                store.state.tagsList.splice(0, index + 1);
            } else {
                store.state.tagsList.splice(index + 1);
            }
        } else {
            store.state.tagsList.splice(0, index);
        }
        this.activeTab(store.state.tagsList[0])
    }

    // 关闭所有 或者 关闭指定
    closeTabs(index) {
        if (!index && index !== 0) {
            store.state.tagsList.splice(0);
            return;
        }
        // 操作后会导致所有的tags重新刷一遍
        let tags = store.state.tagsList.splice(index, 1);
        // 非当前激活tab 则直接确认
        if (!this.isActive(tags[0].id)) {
            return;
        }
        // 剩下的tab
        let total = store.state.tagsList.length;
        if (total === 0) {
            return;
        }
        if (index < total) {
            this.activeTab(store.state.tagsList[index])
        } else {
            this.activeTab(store.state.tagsList[total - 1])
        }
    }
}

const adminTabService = new AdminTabService()

export default adminTabService
