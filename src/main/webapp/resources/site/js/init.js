var init = { 
     'questions': [ 
       {
           'question': '你抽烟吗？',
           'answers': ['A.天天抽烟','B.偶尔抽烟','C.从不抽烟','D.高兴的时候就抽'],
		   'correctAnswer': 1
       },
       {
           'question': '你的牙齿经常出血吗?',
           'answers': ['A.经常','B.偶尔','C.从不'],
		   'correctAnswer': 2
       },
		 {
           'question': '你的牙齿的颜色',
           'answers': ['A.洁白','B.偏黄','C.黑色','D.灰色'],
		   'correctAnswer': 4
       }
     ],
	  'resultComments' :  
	  {
		     perfect: '你的牙齿保持不错，请继续注意健康！',
			 excellent: '你的牙齿有点偏黄，注意蛀牙的形成，推荐我们的细齿洁!',
			 good: '你应该少抽烟，经常清洁牙齿！',
			 average: '你的牙齿是敏感性牙齿，请用XX牙膏！',
			 bad: 'Well, that was poor.',
			 poor: 'Dreadful!',
			 worst: 'For shame, troll!'
	  }
 };