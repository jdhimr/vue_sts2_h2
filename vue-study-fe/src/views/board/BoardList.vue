<template>
    <div class="board-list">
      <div class="common-buttons">
        <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnWrite">등록</button>
      </div>
      <table class="w3-table-all">
        <thead>
        <tr>
          <th>No</th>
          <th>제목</th>
          <th>작성자</th>
          <th>등록일시</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(row, idx) in list" :key="idx">
          <td>{{ row.idx }}</td>
          <td><a v-on:click="fnView(`${row.idx}`)">{{ row.title }}</a></td>
          <td>{{ row.author }}</td>
          <td>{{ row.created_at }}</td>
        </tr>
        </tbody>
      </table>
      <div class="pagination w3-bar w3-padding-16 w3-small" v-if="paging.totalListCnt > 0">
        <span class="pg">
        <a href="javascript:;" @click="fnPage(1)" class="first w3-button w3-bar-item w3-border">&lt;&lt;</a>
        <a href="javascript:;" v-if="paging.startPage > 10" @click="fnPage(`${paging.startPage-1}`)"
           class="prev w3-button w3-bar-item w3-border">&lt;</a>
        <template v-for=" (n,index) in paginavigation()">
            <template v-if="paging.page==n">
                <strong class="w3-button w3-bar-item w3-border w3-green" :key="index">{{ n }}</strong>
            </template>
            <template v-else>
                <a class="w3-button w3-bar-item w3-border" href="javascript:;" @click="fnPage(`${n}`)" :key="index">{{ n }}</a>
            </template>
        </template>
        <a href="javascript:;" v-if="paging.totalPageCnt > paging.endPage"
           @click="fnPage(`${paging.endPage+1}`)" class="next w3-button w3-bar-item w3-border">&gt;</a>
        <a href="javascript:;" @click="fnPage(`${paging.totalPageCnt}`)" class="last w3-button w3-bar-item w3-border">&gt;&gt;</a>
        </span>
      </div>
    </div>

    <div>
        <select v-model="searchKey">
            <option value=""> - 선택 -</option>
            <option value="author">작성자</option>
            <option value="title">제목</option>
            <option value="contents">내용</option>
        </select>
        &nbsp;
        <input type="text" v-model="searchValue" @keyup.enter="fnPage()">
        &nbsp;
        <button @click="fnPage()">검색</button>
    </div>
  </template>

<script>
export default {
    data() { //변수생성
        return {
            requestBody: {}, //리스트 페이지 데이터 전송
            list: {},   //리스트 데이터
            no: '', //게시판 숫자처리
            paging: {
                block: 0,
                endPage: 0, //end_page:
                nextBlock: 0,   //next_block
                page: 0,
                pageSize: 0,    //page_size
                prevBlock: 0,   //prev_block
                startIndex: 0, //start_index
                startPage: 0,   //start_page
                totalBlockCnt: 0, //total_block_cnt
                totalListCnt: 0,  //total_list_cnt
                totalPageCnt: 0,    //total_page_cnt
            },  //페이징 데이터
            page: this.$route.query.page ? this.$route.query.page: 1,
            size: this.$route.query.size ? this.$route.query.size: 10,
            
            searchKey: this.$route.query.sk ? this.$route.query.sk: '',
            searchValue: this.$route.query.sv ? this.$route.query.sv: '',
            
            paginavigation: function () {   //페이징 처리 for문 커스텀
                let pageNumber = [];
                //let start_page = this.paging.start_page;
                let startPage = this.paging.startPage;
                //let end_page = this.paging.end_page;
                let endPage = this.paging.endPage;
                //for (let i = start_page; i <= end_page; i++) pageNumber.push(i);
                for (let i = startPage; i <= endPage; i++) pageNumber.push(i);
                return pageNumber;
            }
        }

    },
    mounted() {
        this.fnGetList()
    },
    methods: {
        fnGetList() {
            // this.list = [
            //     {
            //         "idx":1,
            //         "title": "제목1",
            //         "author": "작성자1",
            //         "created_at": "작성일시1"
            //     },
            //     {
            //         "idx":1,
            //         "title": "제목1",
            //         "author": "작성자1",
            //         "created_at": "작성일시1"
            //     },
            //     {
            //         "idx":1,
            //         "title": "제목1",
            //         "author": "작성자1",
            //         "created_at": "작성일시1"
            //     }
            // ]
            this.requestBody = { // 데이터 전송        
            keyword: this.keyword,
            page: this.page,
            size: this.size,
                                        //검색어 및 값 추가해서 전송(0402 by Me)
            sk: this.searchKey,
            sv: this.searchValue,
            
            }

            this.$axios.get(this.$serverUrl + "/board/list", {
                params: this.requestBody,
                headers: {}
            }).then((res) => {      

                if (res.data.resultCode === "OK") { //result_code 에서 수정!!!
                this.list = res.data.data
                this.paging = res.data.pagination
                //this.no = this.paging.total_list_cnt - ((this.paging.page - 1) * this.paging.page_size)
                this.no = this.paging.totalListCnt - ((this.paging.page - 1) * this.paging.pageSize)
                }

            }).catch((err) => {
                if (err.message.indexOf('Network Error') > -1) {
                alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
                }
            })
        },
        fnView(idx) {
            this.requestBody.idx = idx
            this.$router.push({
                path: './detail',
                query: this.requestBody
            })
        },
        fnWrite() {
            this.$router.push({
            path: './write'
            })
        },
        fnPage(n) {
            if (this.page !== n) {
                this.page = n
                this.fnGetList()
            }
        }
    }

}
</script>